package algonquin.cst2335.projmy;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BearImage {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name="id")
    public long id;

    @ColumnInfo(name="message")
    String message;

    @ColumnInfo(name="TimeSent")
    String timeSent;

    @ColumnInfo(name="url")
    String url;

    @ColumnInfo(name="SendOrReceive")
    boolean isSentButton;

    public BearImage(String m, String h, boolean sent)
    {
        message = m;
        timeSent = h;
        url = "https://placebear.com/"+ h +"/"+ m;
        isSentButton = sent;
    }

    public BearImage()
    {}

    public String getMessage() {
        return message;
    }
    public String getTimeSent() {
        return timeSent;
    }
    public String getUrl() {return url;}
    public boolean getIsSentButton() {
        return isSentButton;
    }

}
