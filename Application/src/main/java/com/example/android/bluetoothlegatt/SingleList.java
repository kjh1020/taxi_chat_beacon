package com.example.android.bluetoothlegatt;
import java.util.ArrayList;
import java.util.HashSet;

/*
 * 싱글톤 클래스
 * 여러 액티비티 또는 여러 클래스에서 함께 공유하고 싶은 자원들은 싱글톤 패턴으로 만들어 작업한다.
 * 자바 웹플 할때도 유용하게 쓰임
 *
 * 클래스 안에 리스트를 static 하게 선언하고 쓰면 안되는 이유는 해당 클래스가 또는 액티비티가 살아있는 동안만 변수가 살아있음 고로 글로벌하게 static 한 객체를 만들어놔야 유용하게 쓸 수 있음
 *
 */
public  class SingleList {

    private static HashSet<String> list = new HashSet<String>();//list 객체 초기화 반드시 해줘야함 아니면 널포인트 입셉션 뜸
    private static SingleList instance;//클래스 이름으로 참조형 변수를 만듬


    private SingleList(){//private으로 생성자 함수 만듬 왜냐하면 외부에서 해당 클래스를 인스턴스 만들지 못하게 하기위함  반드시 런타임중에 한개의 인스턴스가 존재하기 위함

    }
    public static SingleList getInstance(){//고로 인스턴스 생성하는 메소드를 따로 구현 instance가 널일때만 만듬 null 이 아닐때 그냥 리턴
        if(instance ==null)
            instance=new SingleList();
        return instance;
    }
    /*
     * 아래는 getter와 add 메소드 만든것이니 생략
     */
    public static HashSet<String> getList() {
        return list;
    }



    public static void add(String mvo){
        list.add(mvo);
    };


    public static  void clear(){
        list.clear();
    }




}
