package uol.bt.commons.test.helper;

import uol.bt.commons.test.util.CommonsConstants;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;

public class MongoHelper {

    private static DB db = null;

    public static DB connect() throws Exception {
        if (db == null) {
            final MongoOptions opt = new MongoOptions();
            opt.setAutoConnectRetry(CommonsConstants.MONGO_OPT_AUTO_CONN_RETRY);
            opt.setConnectionsPerHost(CommonsConstants.MONGO_OPT_CONN_PER_HOST);
            opt.setConnectTimeout(CommonsConstants.MONGO_OPT_CONN_TIMEOUT);
            opt.setMaxWaitTime(CommonsConstants.MONGO_OPT_MAX_WAIT_TIME);
            opt.setSocketTimeout(CommonsConstants.MONGO_OPT_SOCKET_TIMEOUT);
            opt.setThreadsAllowedToBlockForConnectionMultiplier(CommonsConstants.MONGO_OPT_THREADS_ALLOWED_BLOCK);

            final ServerAddress address = new ServerAddress(CommonsConstants.MONGO_ROUTER_HOST, CommonsConstants.MONGO_ROUTER_PORT);

            db = new Mongo(address, opt).getDB(CommonsConstants.MONGO_DB);

            boolean auth = db.authenticate("manut_bt", "manut".toCharArray());
            if (!auth) {
                throw new IllegalStateException("impossivel autenticar no MongoDB com as credenciais fornecidas");
            }
        }

        return db;
    }

    public static void createCollection(String collectionName) throws Exception {
        final DB db = connect();
        final String collectionToShard = String.format("%s.%s", db.getName(), collectionName);

        final BasicDBObject shardCommand = new BasicDBObject("shardcollection", collectionToShard)
        .append("key", new BasicDBObject("_id", 1))
        .append("unique", true);

        try {
            db.requestStart();
            db.getMongo().getDB("admin").command(shardCommand);
        } finally {
            db.requestDone();
        }
    }

    public static void dropCollection(String collName) throws Exception {
        final DB db = connect();
        try {
            db.requestStart();
            db.getCollection(collName).drop();
        } finally {
            db.requestDone();
        }
    }

    public static boolean collectionExists(String collName) throws Exception {
        final DB db = connect();
        try {
            db.requestStart();

            return db.collectionExists(collName);
        } finally {
            db.requestDone();
        }
    }

    public static DBObject findOneInCollection(String docId, String collection) throws Exception {
        final DB db = connect();
        try {
            db.requestStart();

            return db.getCollection(collection).findOne(new BasicDBObject("_id", docId));
        } finally {
            db.requestDone();
        }
    }

    public static WriteResult removeFromCollection(String docId, String collection) throws Exception {
        final DB db = connect();
        try {
            db.requestStart();
            final DBObject document = new BasicDBObject("_id", docId);

            return db.getCollection(collection).remove(document, WriteConcern.SAFE);
        } finally {
            db.requestDone();
        }
    }

    public static WriteResult upsertIntoCollection(String id, DBObject updateObject, String collectionName) throws Exception {
        final BasicDBObject queryById = new BasicDBObject("_id", id);
        final DB db = connect();
        try {
            db.requestStart();

            return db.getCollection(collectionName).update(queryById, updateObject, true, false);
        } finally {
            db.requestDone();
        }
    }

    public static WriteResult saveInCollection(DBObject dbObject, String collectionName) throws Exception {
        final DB db = connect();
        try {
            db.requestStart();

            return db.getCollection(collectionName).save(dbObject, WriteConcern.SAFE);
        } finally {
            db.requestDone();
        }
    }
}
