package com.android.common.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.android.common.bean.Task;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TASK".
*/
public class TaskDao extends AbstractDao<Task, Void> {

    public static final String TABLENAME = "TASK";

    /**
     * Properties of entity Task.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Title = new Property(0, String.class, "title", false, "TITLE");
        public final static Property StartDate = new Property(1, String.class, "startDate", false, "START_DATE");
        public final static Property Content = new Property(2, String.class, "content", false, "CONTENT");
        public final static Property EndDate = new Property(3, String.class, "endDate", false, "END_DATE");
        public final static Property Sender = new Property(4, String.class, "sender", false, "SENDER");
        public final static Property Receiver = new Property(5, String.class, "receiver", false, "RECEIVER");
        public final static Property TeamName = new Property(6, String.class, "teamName", false, "TEAM_NAME");
        public final static Property Priority = new Property(7, int.class, "priority", false, "PRIORITY");
    }


    public TaskDao(DaoConfig config) {
        super(config);
    }
    
    public TaskDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TASK\" (" + //
                "\"TITLE\" TEXT," + // 0: title
                "\"START_DATE\" TEXT," + // 1: startDate
                "\"CONTENT\" TEXT," + // 2: content
                "\"END_DATE\" TEXT," + // 3: endDate
                "\"SENDER\" TEXT," + // 4: sender
                "\"RECEIVER\" TEXT," + // 5: receiver
                "\"TEAM_NAME\" TEXT," + // 6: teamName
                "\"PRIORITY\" INTEGER NOT NULL );"); // 7: priority
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TASK\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Task entity) {
        stmt.clearBindings();
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(1, title);
        }
 
        String startDate = entity.getStartDate();
        if (startDate != null) {
            stmt.bindString(2, startDate);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(3, content);
        }
 
        String endDate = entity.getEndDate();
        if (endDate != null) {
            stmt.bindString(4, endDate);
        }
 
        String sender = entity.getSender();
        if (sender != null) {
            stmt.bindString(5, sender);
        }
 
        String receiver = entity.getReceiver();
        if (receiver != null) {
            stmt.bindString(6, receiver);
        }
 
        String teamName = entity.getTeamName();
        if (teamName != null) {
            stmt.bindString(7, teamName);
        }
        stmt.bindLong(8, entity.getPriority());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Task entity) {
        stmt.clearBindings();
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(1, title);
        }
 
        String startDate = entity.getStartDate();
        if (startDate != null) {
            stmt.bindString(2, startDate);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(3, content);
        }
 
        String endDate = entity.getEndDate();
        if (endDate != null) {
            stmt.bindString(4, endDate);
        }
 
        String sender = entity.getSender();
        if (sender != null) {
            stmt.bindString(5, sender);
        }
 
        String receiver = entity.getReceiver();
        if (receiver != null) {
            stmt.bindString(6, receiver);
        }
 
        String teamName = entity.getTeamName();
        if (teamName != null) {
            stmt.bindString(7, teamName);
        }
        stmt.bindLong(8, entity.getPriority());
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public Task readEntity(Cursor cursor, int offset) {
        Task entity = new Task( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // title
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // startDate
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // content
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // endDate
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // sender
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // receiver
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // teamName
            cursor.getInt(offset + 7) // priority
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Task entity, int offset) {
        entity.setTitle(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setStartDate(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setContent(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setEndDate(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setSender(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setReceiver(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setTeamName(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setPriority(cursor.getInt(offset + 7));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(Task entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(Task entity) {
        return null;
    }

    @Override
    public boolean hasKey(Task entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}