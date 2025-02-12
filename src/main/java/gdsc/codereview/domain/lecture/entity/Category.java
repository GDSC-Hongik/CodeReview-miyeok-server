package gdsc.codereview.domain.lecture.entity;

import lombok.Getter;

@Getter
public enum Category {
    WEB("Web Development","https://www.inflearn.com/courses/it-programming/web-dev?sort=RECENT"),
    MOBILE("Mobile Development","https://www.inflearn.com/courses/it-programming/mobile-app?sort=RECENT"),
    PL("Programming Language","https://www.inflearn.com/courses/it-programming/programming-lang?sort=RECENT"),
    DB("Database","https://www.inflearn.com/courses/it-programming/database-dev?sort=RECENT"),
    TEST("Software Testing","https://www.inflearn.com/courses/it-programming/sw-test?sort=RECENT");

    private final String displayName;
    private final String url;

    Category(String displayName,String url){
        this.displayName = displayName;
        this.url = url;
    }
}
