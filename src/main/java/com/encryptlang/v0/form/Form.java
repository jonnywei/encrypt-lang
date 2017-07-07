package com.encryptlang.v0.form;

import com.encryptlang.v0.env.Environment;

/**
 * Created by wjj on 6/10/17.
 */
public interface Form {
    /**
     * eval
     * @return
     */
    Object eval(Environment env);

    class SymbolForm implements Form {

        private final  String name;
        public SymbolForm(String name) {
            this.name = name;
        }
        @Override
        public String toString() {
            return "'" + this.name;
        }

        @Override public int hashCode() {
            return this.name.hashCode();
        }

        @Override public boolean equals(Object obj) {
            if(obj instanceof SymbolForm && ((SymbolForm) obj).name.equals(this.name)){
                return true;
            }
            return false;
        }
        @Override
        public Object eval(Environment env) {
            return env.getValue(this);
        }
    }


    class StringForm implements Form {

        private String value;
        public StringForm(String value) {
            this.value = value;
        }

        public Object eval(Environment env) {
            return value;
        }

        @Override public int hashCode() {
            return value.hashCode();
        }

        @Override public boolean equals(Object obj) {
            return obj instanceof String && value.equals(obj);
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    class SpecialForm  implements Form{
        private  static  String DEFINE = "define";
        private  static   SymbolForm DEFINE_SYMBOL_FORM = new SymbolForm(DEFINE);

        private static  String ASSIGN = "=";
        private  static   SymbolForm ASSIGN_SYMBOL_FORM = new SymbolForm(ASSIGN);

        private static  String IF = "if";
        private  static   SymbolForm IF_SYMBOL_FORM = new SymbolForm(IF);

        private static  String LAMBDA = "lambda";
        private  static   SymbolForm LAMBDA_SYMBOL_FORM = new SymbolForm(LAMBDA);


        @Override public Object eval(Environment env) {
            return null;
        }


        public static class DefineSpecialForm extends SpecialForm {

            protected final ListForm form;

            private DefineSpecialForm(ListForm form) {
                this.form = form;
            }

            @Override public Object eval(Environment env) {
                SymbolForm symbol = (SymbolForm) this.form.cdr.car;
                env.putValue(symbol, this.form.cdr.cdr.car.eval(env));
                return null;
            }

            @Override public String toString() {
                return form.toString();
            }
        }


        public static class AssignSpecialForm extends SpecialForm {

            protected final ListForm form;

            private AssignSpecialForm(ListForm form) {
                this.form = form;
            }

            @Override public Object eval(Environment env) {
                Form a = this.form.cdr.car;
                if(this.form.cdr.cdr != ListForm.EMPTY){
                    throw  new IllegalArgumentException("quote part number must be 1");
                }
                return a;
            }

            @Override public String toString() {
                return form.toString();
            }
        }

        //(if test-expr then-expr else-expr)

        public static class IfSpecialForm extends SpecialForm {

            protected final ListForm form;

            private IfSpecialForm(ListForm form) {
                this.form = form;
            }

            @Override public Object eval(Environment env) {
                if(this.form.length() != 4){
                    throw  new IllegalArgumentException("if part number must be 3");
                }
                Form test_expr = this.form.cdr.car;  //second
                Form then_expr = this.form.cdr.cdr.car; //third
                Form else_expr = this.form.cdr.cdr.cdr.car; //four

                boolean testVal = true;
//                if(test_expr instanceof BooleanForm){
//                    BooleanForm d =  (BooleanForm)  test_expr;
//                    testVal = (Boolean) d.eval(env);
//                }else  if(test_expr instanceof  ListForm && test_expr ==ListForm.EMPTY){
//                    testVal = false;
//                }else  if(test_expr instanceof  NumberForm){
//                    testVal =  ((Long) (  ( (NumberForm)test_expr ).eval(env) ) ).longValue() != 0;
//                }

                if(testVal){
                    return  then_expr.eval(env);
                }else {
                    return  else_expr.eval(env);
                }
            }

            @Override public String toString() {
                return form.toString();
            }
        }

//        public static class LambdaSpecialForm extends SpecialForm {
//
//            protected final ListForm form;
//
//            private LambdaSpecialForm(ListForm form) {
//                this.form = form;
//            }
//
//            @Override public Object eval(Environment env) {
//                ListForm parm_expr = (ListForm) this.form.cdr.car;  //second
//                Form body_expr = this.form.cdr.cdr.car; //third
//                //            env.putValue(symbol, this.form.cdr.cdr.car.eval(env));
//
//                return new Function() {
//
//                    Environment lambdaEnv = new Environment(env);
//                    @Override public Object apply(Object... args) {
//                        if(args.length !=  parm_expr.length()){
//                            throw  new IllegalArgumentException("lambda parameter error");
//                        }
//                        // put param into env
//                        int idx =0;
//                        for(Form form : parm_expr){
//                            lambdaEnv.putValue((SymbolForm) form, args[idx++]);
//                        }
//                        return body_expr.eval(lambdaEnv);
//                    }
//                    @Override public Object eval(Environment env) {
//                        return this;
//                    }
//                }
//                        ;
//            }
//
//            @Override public String toString() {
//                return form.toString();
//            }
//        }


        public static Form check(ListForm l){
            if(l == ListForm.EMPTY){
                return l;
            }
            if(l.car.equals(SpecialForm.DEFINE_SYMBOL_FORM)){
                return new DefineSpecialForm(l);
            }else if (l.car.equals(SpecialForm.ASSIGN_SYMBOL_FORM)){
                return new AssignSpecialForm(l);
            }else if (l.car.equals(SpecialForm.IF_SYMBOL_FORM)){
                return new IfSpecialForm(l);
            }else if (l.car.equals(SpecialForm.LAMBDA_SYMBOL_FORM)){
//                return new LambdaSpecialForm(l);
            }


            return l;
        }


    }

}
