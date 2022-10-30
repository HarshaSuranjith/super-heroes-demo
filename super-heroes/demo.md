```
Villain villain = new Villain();
villain.name = "Lex Luthor";
villain.level = 9;

// persist it
villain.persist();

// getting a list of all Villain entities
List<Villain> villains = Villain.listAll();

// finding a specific villain by ID
villain = Villain.findById(id);

// counting all villains
long countAll = Villain.count();
```


```
MANDATORY
If called outside a transaction context, a TransactionalException with a nested TransactionRequiredException must be thrown.
NEVER
If called outside a transaction context, managed bean method execution must then continue outside a transaction context.
NOT_SUPPORTED
If called outside a transaction context, managed bean method execution must then continue outside a transaction context.
REQUIRED
If called outside a transaction context, the interceptor must begin a new JTA transaction, the managed bean method execution must then continue inside this transaction context, and the transaction must be completed by the interceptor.
REQUIRES_NEW
If called outside a transaction context, the interceptor must begin a new JTA transaction, the managed bean method execution must then continue inside this transaction context, and the transaction must be completed by the interceptor.
SUPPORTS
If called outside a transaction context, managed bean method execution must then continue outside a transaction context.
```