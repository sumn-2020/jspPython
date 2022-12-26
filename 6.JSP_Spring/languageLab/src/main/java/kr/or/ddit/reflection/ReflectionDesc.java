package kr.or.ddit.reflection;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import kr.or.ddit.reflect.ReflectionTest;

/**
 * Reflection(java.lang.reflect)
 * 	:객체의 타입, 객체의 속성 정보(상태정보, 행동정보)들을 역으로 추적하는 작업
 *	
 *	:구체적인 클래스 타입을 알지 못해도 그 클래스의 메소드, 타입, 변수들에 접근할 수 있도록 해주는 자바 API
 *
 *
 *
 */


public class ReflectionDesc {
	public static void main(String[] args) {
		
		 Object dataObj = ReflectionTest.getObject();
//		 System.out.println(dataObj);
		 
		 
		 Class<?> objType =  dataObj.getClass(); 
		 //붕어빵 틀에 대한 정보를 레퍼런스로 가지고있음 
		 //객체의 구체적 타입을 모름 => Class<?> 
		 //reflection은 정확한게 하나도 없음 => 그걸 찾아가는 과정이 reflection임 
//		 System.out.println(objType.getName());
	
		 Field[] fields = objType.getDeclaredFields();
//		 Arrays.stream(fields)
//		 		.forEach(System.out::println);
		 
		 
		 //값을 꺼낼때 - 해당 객체가 어떤값이 있고 그 값에 대한 getter와 setter가 어떤게 있는지 확인할 수 있음 
		 Method[] methods = objType.getDeclaredMethods();
//		 Arrays.stream(methods) //Arrays에 methods를 넘기고 
//		 	   .forEach(System.out::println);
		 
		 
		 try {
			
			 Object newObj = objType.newInstance(); //붕어빵에 새로운 인스턴스를 만들기 
			 //System.out.println(newObj);
			 
			 //dataObj와 newObj를 동일하게 바꿔주기 
			 Arrays.stream(fields)
			 	   .forEach(fld->{
			 		  fld.setAccessible(true); //setAccessible()은 필드나 메서드의 접근제어 지시자에 의한 제어를 변경한다.
			 		 
			 		   String fldName = fld.getName();  
			 		  // field 이름: mem_id
			 		  // getter 이름: getMem_id
			 		  // setter 이름: setMem_id
			 		   //System.out.println(fldName);
			 		
			 		  
			 		  //값 꺼내오기
			 		  try {
			 			  PropertyDescriptor pd = new PropertyDescriptor(fldName, objType); //전역변수 한개에 대한 정보를 가진 객체
				 		  Method getter =  pd.getReadMethod(); //getter 
				 		  Method setter =  pd.getWriteMethod(); //setter
				 		  
				 	
		
			 			//getter찾기
//						Object fldValue =  fld.get(dataObj);
						Object fldValue =  getter.invoke(dataObj);
						System.out.println(fldValue);
						
						
						//setter찾기
//						fld.set(newObj, fldValue);
						setter.invoke(newObj, fldValue);
						
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					} catch (IntrospectionException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 	   });
			 
			 
			 //System.out.println(newObj);
			 
		 } catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}