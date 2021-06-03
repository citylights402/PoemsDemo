package com.zhou.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 */
    @Data
    public class Result implements Serializable {//序列化，这是异步返回结果
        private String code;//是否成功
        private String msg;//返回消息
        private Object data;//结果数据

        //    public void setCode(String code) {
//        this.code = code;
//    }
        public static Result succ(Object data){

            return succ("200","成功",data);
        }

        public static Result succ(String code, String msg, Object data){
            Result result=new Result();
            result.setCode(code);
            result.setMsg(msg);
            result.setData(data);
            return result;
        }
        //再封装，实现传入参数不定
        public static Result fail( String msg){
            return fail("400",msg);
        }
        public static Result fail( String msg,Object data){
            return fail("400",msg,data);
        }

        public static Result fail(String code, String msg, Object data){
            Result result=new Result();
            result.setCode(code);
            result.setMsg(msg);
            result.setData(data);
            return result;
        }
}
