package com.encryptlang.form;


import com.encryptlang.env.Environment;
import com.encryptlang.node.Function;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wjj on 6/10/17.
 */
public class ListForm implements Form ,Iterable<Form> {

    public static final ListForm EMPTY = new ListForm(null, null);

    public Form car;

    public ListForm cdr;

    public ListForm() {
        car = null;
        cdr = null;
    }

    public ListForm(Form car, ListForm cdr) {
        this.car = car;
        this.cdr = cdr;
    }

    public ListForm cons(Form form) {
        return new ListForm(form, this);
    }


    public long length(){
        long length =0;
        if(this == ListForm.EMPTY){
            return 0;
        }
        for(Form f : this){
            length ++;
        }
        return length;
    }

    public static ListForm list(List<Form> forms){
        ListForm l = ListForm.EMPTY;
        for(int i= forms.size()-1; i >=0;  i--){
            l = l.cons(forms.get(i));
        }
        return l;
    }

    public Object eval(Environment env) {
        Function function = (Function) this.car.eval(env);
        if(function == null){
            throw  new UnsupportedOperationException( car.toString() + " no such function");
        }
        List<Object> args = new ArrayList<>();
        for(Form xx : this.cdr){
            args.add(xx.eval(env));
        }
        return function.apply(args.toArray());
    }

    @Override
    public String toString() {
        if(this == EMPTY){
            return "()";
        }
        return "(" +
                " " +   car +
                " , " + cdr +
                " )";
    }

    @Override
    public boolean equals(Object obj) {

        if(! (obj instanceof ListForm) ) {
            return false;
        }
        if( this == EMPTY && obj == EMPTY){
            return true;
        }
        ListForm that = (ListForm) obj;

        if(this.cdr ==EMPTY && that.cdr != EMPTY ){
            return false;
        }
//        System.out.println(this.car.equals(that.car));
        return this.car.equals(that.car) && this.cdr.equals(that.cdr);

    }

    @Override public Iterator<Form> iterator() {

         return new Iterator<Form>() {
            ListForm  inner = ListForm.this;

            @Override public boolean hasNext() {
                return inner != ListForm.EMPTY;
            }

            @Override public Form next() {
                Form current = inner.car;
                inner = inner.cdr;
                return current;
            }
        };
    }
}
