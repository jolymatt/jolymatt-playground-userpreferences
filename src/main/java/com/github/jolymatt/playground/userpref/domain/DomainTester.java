package com.github.jolymatt.playground.userpref.domain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DomainTester {

    public static void main(String args[]){

        String[][] str = {{"hello","lll"},{null}};
         for(String[] st:str){
            for(String st1:st){
                log.info(st1);
            }
         }
    }




}
