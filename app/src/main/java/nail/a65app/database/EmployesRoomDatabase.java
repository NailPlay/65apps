package nail.a65app.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Employes.class}, version = 1)
public abstract class EmployesRoomDatabase extends RoomDatabase {

    public abstract EmployesDao employesDao();

    private static EmployesRoomDatabase INSTANCE;

    public static EmployesRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (EmployesRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            EmployesRoomDatabase.class, "employes")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };


}