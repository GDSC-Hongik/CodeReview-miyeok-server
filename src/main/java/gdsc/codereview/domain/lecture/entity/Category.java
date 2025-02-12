package gdsc.codereview.domain.lecture.entity;

import lombok.Getter;

@Getter
public enum Category {
    WEB("Web Development","https://www.inflearn.com/courses/it-programming/web-dev?sort=RECENT",11L),
    MOBILE("Mobile Development","https://www.inflearn.com/courses/it-programming/mobile-app?sort=RECENT",4L),
    PL("Programming Language","https://www.inflearn.com/courses/it-programming/programming-lang?sort=RECENT",9L),
    DB("Database","https://www.inflearn.com/courses/it-programming/database-dev?sort=RECENT",3L),
    TEST("Software Testing","https://www.inflearn.com/courses/it-programming/sw-test?sort=RECENT",1L);

    private final String displayName;
    private final String url;
    private final Long pageN;

    Category(String displayName, String url, Long pageN) {
        this.displayName = displayName;
        this.url = url;
        this.pageN = pageN;
    }

}
