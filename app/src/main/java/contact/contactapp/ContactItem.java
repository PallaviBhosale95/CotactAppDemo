package contact.contactapp;

public class ContactItem {
    String name;
    String mobileNo;
    long id;

    public ContactItem() {
    }

    public ContactItem(String name, String mobileNo) {
        this.name = name;
        this.mobileNo = mobileNo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
