public class Main {
    public static void main(String[] args) {
        InMemoryDB inmemoryDB = new InMemoryDB();

// should return null, because A doesn’t exist in the DB yet
        System.out.println(inmemoryDB.get("A"));

// should throw an error because a transaction is not in progress
        try{
            inmemoryDB.put("A", 5);
        } catch (IllegalStateException e){
            System.out.println(e.getMessage());
        }

// starts a new transaction
        inmemoryDB.begin_transaction();

// set’s value of A to 5, but its not committed yet
        inmemoryDB.put("A", 5);

// should return null, because updates to A are not committed yet
        System.out.println(inmemoryDB.get("A"));

// update A’s value to 6 within the transaction
        inmemoryDB.put("A", 6);

// commits the open transaction
        inmemoryDB.commit();

// should return 6, that was the last value of A to be committed
        System.out.println(inmemoryDB.get("A"));

// throws an error, because there is no open transaction
        try{
            inmemoryDB.commit();
        } catch (IllegalStateException e){
            System.out.println(e.getMessage());
        }

// throws an error because there is no ongoing transaction
        try{
            inmemoryDB.rollback();
        } catch (IllegalStateException e){
            System.out.println(e.getMessage());
        }

// should return null because B does not exist in the database
        System.out.println(inmemoryDB.get("B"));

// starts a new transaction
        inmemoryDB.begin_transaction();

// Set key B’s value to 10 within the transaction
        inmemoryDB.put("B", 10);

// Rollback the transaction - revert any changes made to B
        inmemoryDB.rollback();

// Should return null because changes to B were rolled back
        System.out.println(inmemoryDB.get("B"));

    }
}