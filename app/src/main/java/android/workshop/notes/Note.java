package android.workshop.notes;


import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {

    private String title;
    private String content;

    public Note() {
        title = "";
        content = "";
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(content);
    }

    protected Note(Parcel in) {
        title = in.readString();
        content = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
}
