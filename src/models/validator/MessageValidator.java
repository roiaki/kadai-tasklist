package models.validator;

import java.util.ArrayList;
import java.util.List;

import models.Task;

public class MessageValidator {


    public static List<String> validate(Task t) {
        List<String> errors = new ArrayList<String>();

        String title_error = _validateTitle(t.getTitle());
        if(!title_error.equals("")) {
            errors.add(title_error);
        }

        String content_error = _validateContent(t.getContent());
        if(!content_error.equals("")) {
            errors.add(content_error);
        }

        return errors;

    }

    private static String _validateTitle(String title) {
        if(title == null || title.equals("")) {
            return "タイトルを入力してください";
        }

        return "";
    }

    private static String _validateContent(String content) {
        if(content == null || content.equals("")) {
            return "内容を入力してください";
        }

        return "";
    }

}
