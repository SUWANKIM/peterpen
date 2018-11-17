package com.yj.kakao.afeel.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import java.io.File;
import java.io.Serializable;

public class UserManager implements Serializable {

    private static UserManager userManager;

    /**
     * 객체 얻기
     *
     * @return
     */
    public static UserManager getInstance() {
        if (userManager == null) {
            userManager = new UserManager();
        }

        return userManager;
    }

    private UserManager() {
        releaseUserData();
    }

    public static final int USER_LEVEL_MEMBER = 1;
    public static final int USER_LEVEL_MASTER = 2;

    /**
     * 유저 번호*자료기지 index
     */
    public int member_no;
    /**
     * 유저 아이디 * ex:2348923
     */
    public String member_id = "";

    public String member_password = "";
    /**
     * 유저 닉네임*
     */
    public String member_nick_name = "";

    public String member_photo = "";//서버 url

    public String member_name = "";

    public int member_sex; //0.male, 1:female

    public String member_birth = "";

    //회원가입시 이용
    public File member_photo_file = null;
    public Bitmap member_photo_bitmap = null;
    public int member_photo_resID = -1;

    /**
     * 유저정보 초기화
     */
    public void releaseUserData() {
        member_no = 0;
        member_id = "";
        member_password = "";
        member_nick_name = "";
        member_photo = "";
        member_name = "";
        member_sex = 0;
        member_birth = "";
        member_photo_file = null;
        member_photo_bitmap = null;
        member_photo_resID = -1;
    }

    public void saveData(Context context, String type, String id, String password) {
        //
        SharedPreferences pref = context.getSharedPreferences("AFeel", 0);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("type", type);
        edit.putString("id", id);
        edit.putString("pass", password);
        edit.commit();
    }

    public void setUser(UserManager user) {
        member_no = user.member_no;
        member_id = user.member_id;
        member_password = user.member_password;
        member_nick_name = user.member_nick_name;
        member_photo = user.member_photo;
        member_name = user.member_name;
        member_sex = user.member_sex;
        member_birth = user.member_birth;
        member_photo_file = user.member_photo_file;
        member_photo_bitmap = user.member_photo_bitmap;
        member_photo_resID = user.member_photo_resID;
    }
}
