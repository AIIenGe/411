import Model.Coordinate;
import akka.actor.ActorSystem;
import controllers.*;
import org.junit.Test;
import play.mvc.Result;
import play.twirl.api.Content;
import scala.concurrent.ExecutionContextExecutor;

import static org.mockito.Mockito.*;

import java.util.concurrent.CompletionStage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static play.mvc.Http.Status.OK;

import static org.awaitility.Awaitility.await;
import static play.test.Helpers.contentAsString;

/**
 * Unit testing does not require Play application start up.
 *
 * https://www.playframework.com/documentation/latest/JavaTest
 */
public class UnitTest {

    @Test
    public void simpleCheck() {
        int a = 1 + 1;
        assertThat(a).isEqualTo(2);
    }

    // Unit test a controller
    @Test
    public void testCount() {
        final CountController controller = new CountController(() -> 49);
        Result result = controller.count();
        assertThat(contentAsString(result)).isEqualTo("49");
    }

    // Unit test a controller with async return
    @Test
    public void testAsync() {
        final ActorSystem actorSystem = ActorSystem.create("test");
        try {
            final ExecutionContextExecutor ec = actorSystem.dispatcher();
            final AsyncController controller = new AsyncController(actorSystem, ec);
            final CompletionStage<Result> future = controller.message();

            // Block until the result is completed
            await().untilAsserted(() ->
                    assertThat(future.toCompletableFuture())
                            .isCompletedWithValueMatching(result -> contentAsString(result).equals("Hi!"))
            );
        } finally {
            actorSystem.terminate();
        }
    }

    //first test of three (1/3)
    @Test
    public void testController() {
        Result result = new HomeController().index();
        assertEquals(OK, result.status());
        assertEquals("text/html", result.contentType().get());
        assertEquals("utf-8", result.charset().get());
        assertTrue(contentAsString(result).contains("Welcome"));
    }

    //second test of three (2/3)
    @Test
    public void renderHistoryTemplate() {
        Content html = views.html.history.render("History");
        assertEquals("text/html", html.contentType());
        assertTrue(contentAsString(html).contains("History"));
    }

    //third test of three (3/3)
    @Test
    public void testWithMock() {
        Coordinate coordinateMock = mock(Coordinate.class);
        when(coordinateMock.getLongitude()).thenReturn(33.33);
        assertEquals(coordinateMock.getLongitude(), 33.33, .001);
    }
}