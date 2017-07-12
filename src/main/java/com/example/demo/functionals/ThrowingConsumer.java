package com.example.demo.functionals;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface ThrowingConsumer<T, U> extends BiConsumer<T, U> {
    
	
	@Override
    default void accept(T t, U u) {
    	try{
    		acceptThrows(t, u);
    	}catch(Exception e){
				throw new RuntimeException();
    	}    	
    }
    void acceptThrows(T t, U u) throws Exception;

}

