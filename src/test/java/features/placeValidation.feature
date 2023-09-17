Feature: Testing API Endpoint

  Scenario Outline: Create add new book
    Given I have the API endpoint <arg0> and resource <arg1>
    When the response status code should be <statusCode>
    Then  Response should contain message successfully added
    Examples:
      | arg0                    | arg1                   | statusCode |
      | "http://216.10.245.166" | "/Library/Addbook.php" | 200        |



 Scenario: Add Existing book
    Given User call "http://216.10.245.166/Library/Addbook.php" for creating new book and doesn't provide bookID existing "test"
    When  Response return status code should be 200
    Then  Response meesage sholud be "testtest"
