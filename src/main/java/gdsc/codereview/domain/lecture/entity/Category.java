package gdsc.codereview.domain.lecture.entity;

import lombok.Getter;

@Getter
public enum Category {
    WEB("Web Development","https://www.inflearn.com/courses/it-programming/web-dev?sort=POPULAR",11L,"https://www.udemy.com/courses/development/web-development/",625L),
    MOBILE("Mobile Development","https://www.inflearn.com/courses/it-programming/mobile-app?sort=POPULAR",4L,"https://www.udemy.com/courses/development/mobile-apps/",189L),
    PL("Programming Language","https://www.inflearn.com/courses/it-programming/programming-lang?sort=POPULAR",9L,"https://www.udemy.com/courses/development/programming-languages/",532L),
    DB("Database","https://www.inflearn.com/courses/it-programming/database-dev?sort=POPULAR",3L,"https://www.udemy.com/courses/development/databases/",129L),
    TEST("Software Testing","https://www.inflearn.com/courses/it-programming/sw-test?sort=POPULAR",1L,"https://www.udemy.com/courses/development/software-testing/",91L);

    private final String displayName;
    private final String inflearnUrl;
    private final Long inflearnPageN;
    private final String udemyUrl;
    private final Long udemyPageN;

    Category(String displayName, String inflearnUrl, Long inflearnPageN, String udemyUrl, Long udemyPageN) {
        this.displayName = displayName;
        this.inflearnUrl = inflearnUrl;
        this.inflearnPageN = inflearnPageN;
        this.udemyUrl = udemyUrl;
        this.udemyPageN = udemyPageN;
    }
}
