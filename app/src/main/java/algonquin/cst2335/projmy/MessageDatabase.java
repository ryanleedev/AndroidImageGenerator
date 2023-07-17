package algonquin.cst2335.projmy;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {BearImage.class}, version=1)
public abstract class MessageDatabase extends RoomDatabase {
    public abstract BearImageDAO cmDAO();
}
