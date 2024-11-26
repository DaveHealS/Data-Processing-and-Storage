import java.util.HashMap;
import java.util.Map;

public class InMemoryDB implements InMemoryDBIntf {
    private Map<String, Integer> mainDatabase;
    private Map<String, Integer> transaction;

    public InMemoryDB() {
        mainDatabase = new HashMap<>();
        transaction = null;
    }

    // get(key) will return the value associated with the key or null if the key doesn’t exist.
    // get(key) can be called anytime even when a transaction is not in progress
    // Within a transaction you can make as many changes to as many keys as you like.
    // However, they should not be “visible” to get(), until the transaction is committed.
    @Override
    public Integer get(String key) {
        if(!mainDatabase.containsKey(key)){
            return null;
        }
        return mainDatabase.get(key);
    }

    // put(key, val) will create a new key with the provided value if a key doesn’t exist.
    // Otherwise it will update the value of an existing key.
    // If put(key, val) is called when a transaction is not in progress throw an exception
    @Override
    public void put(String key, int val) {
        if(transaction == null){
            throw new IllegalStateException("No transaction active!");
        }
        transaction.put(key, val);
    }

    // begin_transaction() starts a new transaction.
    // At a time only a single transaction may exist.
    @Override
    public void begin_transaction() {
        if (transaction != null) {
            throw new IllegalStateException("A Transaction is already active!");
        }
        transaction = new HashMap<>(mainDatabase);
    }

    // A transaction ends when either commit() or rollback() is called
    // commit() applies changes made within the transaction to the main state.
    // Allowing any future gets() to “see” the changes made within the transaction
    @Override
    public void commit() {
        if (transaction == null) {
            throw new IllegalStateException("No transaction exists!");
        }
        mainDatabase = transaction;
        transaction = null;
    }

    // A transaction ends when either commit() or rollback() is called
    // rollback() should abort all the changes made within the transaction
    // and everything should go back to the way it was before.
    @Override
    public void rollback() {
        if (transaction == null) {
            throw new IllegalStateException("No transaction active!");
        }
        transaction = null;
    }
}