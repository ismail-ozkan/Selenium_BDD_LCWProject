Feature: LCW User Purchasing work flow

  Background: User is already on homepage
    Given User goes to LCW homepage
  @TC01
  Scenario: User should be able to add a product on the cart
    When User clicks on Bebek module button
    And User clicks on one of the category on the Bebek page
    And User clicks on first product on the list of products
    And User chooses one of the available sizes
    And User clicks on Sepete Ekle Button
    Then User should be able to see "SEPETE EKLENDİ" message
    And User clicks Sepetim Button
    Then User should see the same product in Sepet page
    And User clicks LC Waikiki logo to go to homepage
    Then User should be able to see homepage

