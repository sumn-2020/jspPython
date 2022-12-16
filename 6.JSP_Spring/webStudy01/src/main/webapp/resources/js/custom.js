/**
 * 
 */




$.fn.serializeObject=function() {//제이쿼리 안에 serializeObject라는 새로운 함수를 갖게 됐음 
	
	if(this.prop('tagName') != 'FORM') //tagName을 가지고있는 객체가 form태그가 아니라면 
		throw Error("form 태그 외에는 사용 불가 ");
	//this에서 tagName이라는 걸 꺼내겠다 
	//   this=> $.fn에서 사용되고있는  객체 
	
	
	
	let fd = new FormData(this[0]); //input 에  작성한 값들~ form에 저장되어있는 데이터들 
	let nameSet = new Set(); //중복 방지, 제거해주기 위해서 
	for(let key of fd.keys()) {
		nameSet.add(key); //중복되는 이름을 가지고 있으면 제거 
	}
	
	
	let data = {}; //비어있는 객체 (data ={} 이런식으로 뒷단으로 넘겨야 되니까)
	for(let name of nameSet) {
		
		//fd.get('writer');
		//fd.get(name); //이름은 하나인데 입력태그 두개일 경우  한개만 꺼내옴
		let values = fd.getAll(name);//이름은 하나인데 입력태그 두개일 경우  두개 다 꺼내올수 있음 
		if(values.length>1){ //값이 여러개일 경우 
			//data.writer;
			//data[name] ; //얼마든지 객체의 구조를 동적으로 만들수 있음 위처럼 하면 writer 하나만 사용할 수 있으니까 
			data[name] = values ;
			
		}else {
			data[name] = values[0]; //배열이 필요가 없음 하나만 가져오면 됨 
		}
	}
	return data;

}



//폼태그의 모든 입력 데이터의 이름과 값을 콘솔에 로그로 출력할 수 있는 함수.
//ex) $("form").log().serializeObject 이렇게 사용 할 수 있게끔 만들어줘야됨 
//$.log
$.fn.log = function() {
	let data = this.serializeObject(); //모든 입력 데이터의 모든 key value값을 다 가지고 있음 
	for(let prop in data) {
		console.log(prop + " : " + data[prop]); //data.writer[prop] : prop속에 들어있는 writer을 꺼내옴
	}
		
	// of: 반복의 대상이 될수 있는 경우 배열..
	// in : 일반객체 일 경우 대신 그 객체가 가지는 프로퍼티 하나하나에 접근할 수 있음
	return this; 
}




















