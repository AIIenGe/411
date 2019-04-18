Three new tests were written in the UnitTest class:
1 testHome()
    - tests the home controller
    - asserts that the index method of the home controller correctly contains the given text, content type, and charset
2 testWithMock()
    - tests Coordinate model
    - verifies that the getLatitude method correctly returns an expected double value
3 redirectHeatMap()
    - tests the redirect to the heatmap route
    - asserts that the addLocation successfully redirects the heatmap route after sending the POST command