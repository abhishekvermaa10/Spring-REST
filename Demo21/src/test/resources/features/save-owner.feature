Feature: Create owners
  Save new owners and their pets

  Scenario Outline: Create a new owner with all required fields
    Given the REST API service "/owners"
    And the HTTP method is POST
    When make the REST API call with body
      """
      {
      "firstName": "<firstName>",
      "lastName": "<lastName>",
      "gender": "<gender>",
      "city": "<city>",
      "state": "<state>",
      "mobileNumber": "<mobileNumber>",
      "emailId": "<emailId>",
      "petDTO": {
                "category": "<pet.category>",
                "name": "<pet.name>",
                "gender": "<pet.gender>",
                "type": "<pet.type>",
                "birthDate": "<pet.birthDate>",
                "birthPlace": "<pet.birthPlace>"
                }
      }
      """
    Then the REST API response has status as 201
    And the REST API response contains owner ID

    Examples: 
      | firstName | lastName | gender | city           | state       | mobileNumber | emailId                          | pet.category | pet.name | pet.gender | pet.type | pet.birthDate | pet.birthPlace           |
      | Abhishek  | Verma    | M      | New Chandigarh | Punjab      |   9009009003 | abhishek.verma@scaleupindia.com  | Domestic     | Mitthu   | M          | BIRD     | 2020-11-28    | null                     |
      | Priyanka  | Chopra   | F      | Mumbai         | Maharashtra |   9009009004 | priyanka.chopra@scaleupindia.com | Wild         | Sweety   | F          | CAT      | null          | Koyna Wildlife Sanctuary |

        Scenario Outline: Create a new owner with missing fields
    Given the REST API service "/owners"
    And the HTTP method is POST
    When make the REST API call with body
      """
      {
      "firstName": "<firstName>",
      "lastName": "<lastName>",
      "gender": "<gender>",
      "city": "<city>",
      "state": "<state>",
      "mobileNumber": "<mobileNumber>",
      "emailId": "<emailId>",
      "petDTO": {
                "category": "<pet.category>",
                "name": "<pet.name>",
                "gender": "<pet.gender>",
                "type": "<pet.type>",
                "birthDate": "<pet.birthDate>",
                "birthPlace": "<pet.birthPlace>"
                }
      }
      """
    Then the REST API response has status as 400
    And the REST API response contains first errorMessage as "<errorMessage>"

    Examples: 
      | firstName | lastName | gender | city           | state  | mobileNumber | emailId                         | pet.category | pet.name | pet.gender | pet.type | pet.birthDate | pet.birthPlace | errorMessage                                             |
      | null      | Verma    | M      | New Chandigarh | Punjab | 9009009009   | abhishek.verma@scaleupindia.com | Domestic     | Mitthu   | M          | BIRD     | 2020-11-28    | null           | First name of owner is mandatory.                        |
      | Abhishek  | null     | M      | New Chandigarh | Punjab | 9009009009   | abhishek.verma@scaleupindia.com | Domestic     | Mitthu   | M          | BIRD     | 2020-11-28    | null           | Last name of owner is mandatory.                         |
      | Abhishek  | Verma    | null   | New Chandigarh | Punjab | 9009009009   | abhishek.verma@scaleupindia.com | Domestic     | Mitthu   | M          | BIRD     | 2020-11-28    | null           | Gender of owner is mandatory.                            |
      | Abhishek  | Verma    | M      | null           | Punjab | 9009009009   | abhishek.verma@scaleupindia.com | Domestic     | Mitthu   | M          | BIRD     | 2020-11-28    | null           | City of owner is mandatory.                              |
      | Abhishek  | Verma    | M      | New Chandigarh | null   | 9009009009   | abhishek.verma@scaleupindia.com | Domestic     | Mitthu   | M          | BIRD     | 2020-11-28    | null           | State of owner is mandatory.                             |
      | Abhishek  | Verma    | M      | New Chandigarh | Punjab | null         | abhishek.verma@scaleupindia.com | Domestic     | Mitthu   | M          | BIRD     | 2020-11-28    | null           | Mobile number of owner is mandatory.                     |
      | Abhishek  | Verma    | M      | New Chandigarh | Punjab | 900900900    | abhishek.verma@scaleupindia.com | Domestic     | Mitthu   | M          | BIRD     | 2020-11-28    | null           | Length of mobile number of owner needs to be 10.         |
      | Abhishek  | Verma    | M      | New Chandigarh | Punjab | 90090090009  | abhishek.verma@scaleupindia.com | Domestic     | Mitthu   | M          | BIRD     | 2020-11-28    | null           | Length of mobile number of owner needs to be 10.         |
      | Abhishek  | Verma    | M      | New Chandigarh | Punjab | 9009009009   | null                            | Domestic     | Mitthu   | M          | BIRD     | 2020-11-28    | null           | Email id of owner is mandatory.                          |
      | Abhishek  | Verma    | M      | New Chandigarh | Punjab | 9009009009   | abhishek.verma                  | Domestic     | Mitthu   | M          | BIRD     | 2020-11-28    | null           | Email id of owner is invalid.                            |
      | Abhishek  | Verma    | M      | New Chandigarh | Punjab | 9009009009   | abhishek.verma@scaleupindia.com | Domestic     | null     | M          | BIRD     | 2020-11-28    | null           | Name of pet is mandatory.                                |
      | Abhishek  | Verma    | M      | New Chandigarh | Punjab | 9009009009   | abhishek.verma@scaleupindia.com | Domestic     | Mitthu   | null       | BIRD     | 2020-11-28    | null           | Gender of pet is mandatory.                              |
      | Abhishek  | Verma    | M      | New Chandigarh | Punjab | 9009009009   | abhishek.verma@scaleupindia.com | Domestic     | Mitthu   | M          | null     | 2020-11-28    | null           | Type of pet is mandatory.                                |
      | Abhishek  | Verma    | M      | New Chandigarh | Punjab | 9009009009   | abhishek.verma@scaleupindia.com | Domestic     | Mitthu   | M          | BIRD     | null          | null           | Date of birth of pet is mandatory.                       |
      | Abhishek  | Verma    | M      | New Chandigarh | Punjab | 9009009009   | abhishek.verma@scaleupindia.com | Domestic     | Mitthu   | M          | BIRD     | 2120-11-28    | null           | Date of birth of pet can be a past or present date only. |
      | Abhishek  | Verma    | M      | New Chandigarh | Punjab | 9009009009   | abhishek.verma@scaleupindia.com | Wild         | Mitthu   | M          | BIRD     | null          | null           | Place of birth of pet is mandatory.                      |

  Scenario: Create a new owner without pet
    Given the REST API service "/owners"
    And the HTTP method is POST
    When make the REST API call with body
      """
      {
      "firstName": "Abhishek",
      "lastName": "Verma",
      "gender": "M",
      "city": "New Chandigarh",
      "state": "Punjab",
      "mobileNumber": "9009009009",
      "emailId": "abhishek.verma@scaleupindia.com"
      }
      """
    Then the REST API response has status as 400
    And the REST API response contains first errorMessage as "Pet of owner is mandatory."
      