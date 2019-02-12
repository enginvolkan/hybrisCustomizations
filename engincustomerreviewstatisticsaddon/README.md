## Customer Review Statistics Addon 

###**About**
----------------------

The main purpuse of this extension is to add "Helpful" "Not Helpful" statistics for customer reviews in PDP.
For sake of simplicity, both business logic and UI enhancements are kept in an addon. Service and Facade Layer customizations may be moved to another extension in a real implementation.

###**Requirements**
-----------------------------

1. Two buttons will be displayed under each customer review in PDP for marking the review as either "Helpful" or "Not Helpful".
2. Buttons will only be displayed for registered customers, for guests buttons will not be visible. Guest will see the following remark "Helpful? Please sign in to give feedback on the review".
3. Current statistics will be displayed in button label for each button, such as: Helpful(15) Not Helpful(3).
4. One customer will not be able mark the same review more than once.
5. A customers can not mark his/her own comment.
6. When clicked on the either button, the statistics will immediately be increased and the buttons will be deactivated.
7. Once clicked, the customer will not be able to change his/her decision.
8. Page will not be reloaded with a button click. The message will be sent in the background.

###**Design**
-----------------------

**Data Model**
- Many-to-many relationship between CustomerModel and CustomerReview

**Service Layer**
- Extend DefaultCustomerReviewService to setup the business logic for adding a like or a dislike

**Facade Layer**

**Presentation Layer**