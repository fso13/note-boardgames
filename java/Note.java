package ru.drudenko.sms.testclient;

import java.util.List;

/**
 * created 02/02/2022
 *
 * @author Rudenko Dmitry
 */
public class Note {
    public Integer id;
    public String title;
    public String content;
    public List<String> tags;
    public List<String> files;

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", tags=" + tags +
                ", files=" + files +
                '}';
    }
}
