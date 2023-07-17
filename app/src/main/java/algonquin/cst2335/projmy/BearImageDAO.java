package algonquin.cst2335.projmy;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BearImageDAO {
    @Insert
    public long insertMessage(BearImage m);

    @Query("Select * from BearImage")
    public List<BearImage> getAllMessages();

    @Delete
    void deleteMessage(BearImage m);
}
