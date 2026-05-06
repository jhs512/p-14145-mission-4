package com.back;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

public class WiseSaying {
    private int id;
    private String content;
    private String author;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private static DateTimeFormatter forPrintDateTimeFormatter = AppContext.forPrintDateTimeFormatter;
    public WiseSaying(String content, String author){
        this.content = content;
        this.author = author;
    }
    public void setId(int id){
        this.id =id;
    }
    public int getId(){
        return this.id;
    }
    public String getContent() {
        return this.content;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getCreateDate(){
        return createDate;
    }
    public void setCreateDate(LocalDateTime createDate){
        this.createDate = createDate;
    }
    public LocalDateTime getModifyDate(){
        return modifyDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getForPrintCreateDate(){
        return createDate.format(forPrintDateTimeFormatter);
    }

    public String getForPrintModifyDate(){
        return modifyDate.format(forPrintDateTimeFormatter);
    }

    public boolean isNew(){
        return getId() == 0;
    }
}
