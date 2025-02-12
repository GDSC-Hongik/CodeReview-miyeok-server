package gdsc.codereview.domain.lecture.entity;

import lombok.Getter;

@Getter
public enum Category {
    WEB("Web Development"),
    MOBILE("Mobile Development"),
    PL("Programming Language"),
    DB("Database"),
    TEST("Software Testing");

    private final String displayName;

    Category(String displayName){
        this.displayName = displayName;
    }
}
