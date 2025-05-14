Feature: Fetch owners
  Find owners and their pets by owner's id

  Scenario: Fetch an existing owner with domestic pet
    Given the REST API service "/owners/1"
    And the HTTP method is GET
    When make the REST API call without body
    Then the REST API response has status as 200
    And the REST API response contains owner data
      | id | firstName | lastName | gender | city      | state          | mobileNumber | emailId                   | petDTO.category | petDTO.id | petDTO.name | petDTO.gender | petDTO.type | petDTO.birthDate |
      | 1  | John      | Doe      | M      | Hyderabad | Andhra Pradesh | 9009009001  | john.doe@scaleupindia.com | Domestic        | 1         | Fluffy      | F             | CAT         | 2018-07-26       |

  Scenario: Fetch an existing owner with wild pet
    Given the REST API service "/owners/2"
    And the HTTP method is GET
    When make the REST API call without body
    Then the REST API response has status as 200
    And the REST API response contains owner data
      | id | firstName | lastName | gender | city        | state  | mobileNumber | emailId                       | petDTO.category | petDTO.id | petDTO.name | petDTO.gender | petDTO.type | petDTO.birthPlace         |
      | 2  | William   | Ward     | M      | Bhubaneswar | Odisha | 9009009002   | william.ward@scaleupindia.com | Wild            | 2         | Luna        | F             | CAT         | Jim Corbett National Park |

  Scenario: Fetch an owner which doesn't exist
    Given the REST API service "/owners/1000"
    And the HTTP method is GET
    When make the REST API call without body
    Then the REST API response has status as 404
    And the REST API response contains errorMessage as "Can't find owner with ownerId 1000."

  Scenario: Fetch an owner with invalid ownerId
    Given the REST API service "/owners/-1"
    And the HTTP method is GET
    When make the REST API call without body
    Then the REST API response has status as 400
    And the REST API response contains first errorMessage as "Owner id must be a positive number."
