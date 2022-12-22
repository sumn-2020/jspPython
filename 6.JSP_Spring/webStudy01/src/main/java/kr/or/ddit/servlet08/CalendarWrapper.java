package kr.or.ddit.servlet08;

import java.util.Calendar;
import java.util.Locale;

import  static java.util.Calendar.*; //calender가 가지는 모든 static 멤버는 다 들어있어서  Calendar.DAY_OF_MONTH 이렇게 쓰지 않고 DAY_OF_MONTH 이렇게만 써도 됨 

import java.text.DateFormatSymbols;

public class CalendarWrapper {

	private Calendar adaptee; 
	private Locale locale;
	
	private int offset;
	private int dayOfWeekFirst;
	private int lastDate;
	
	private int currentYear;
	private int currentMonth;
	private int beforeYear;
	private int beforeMonth;
	private int nextYear;
	private int nextMonth;
	
	
	private String[] weekDays;
	private String[] months;
	

	public CalendarWrapper(Calendar adaptee, Locale locale) { //adapter는 adaptee가 없으면 사용못함
		super();
		this.adaptee = adaptee; // 첫번째 파라미터로 받아온 Calendar adaptee
		this.locale = locale; //두번째 파라미터로 받아온 locale
		
		//앞단 스클립틀릿에 있던거 가져옴 
		//*** locale, 요일, 월 받아오기  ***
		//DateFormatSymbols dfs = DateFormatSymbols.getInstance();
		//DateFormatSymbols dfs = DateFormatSymbols.getInstance(Locale.CHINESE); //달력 출력하기 
		DateFormatSymbols dfs = DateFormatSymbols.getInstance(locale); // 컨트롤러에서 locale값 받아서 사용하기 
		weekDays =  dfs.getWeekdays(); //요일받아오기 
		months = dfs.getMonths(); //월 받아오기 

		
		//해당월의 첫번째 날짜 구하기 
		//adaptee의 상태를 12월 1일로 돌려놔야함
		//adaptee.set(Calendar.DAY_OF_MONTH, 1);  
		adaptee.set(DAY_OF_MONTH, 1);    // 자바에서 월은 0부터 시작     (  import  static java.util.Calendar.*; 이렇게 쓰면 위처럼 안쓰고 바로 Day_Of_Month로 쓸수있음 )
										// 일단 adaptee(Calendar)에 month를 1로 세팅해두고 
		
		dayOfWeekFirst = adaptee.get(DAY_OF_WEEK); // adaptee(Calendar)에서  DAY_OF_WEEK가져오기 =>   1일이 일주일중에서 몇 요일인지
		offset = dayOfWeekFirst - SUNDAY; //  week에서 sunday값만큼 빼면 해당월의 1일이 몇칸 떨어져있는지 나옴 =>          offset = 해당월의 1일과 일요일사이에 몇칸이 벌어지는지 찾기
	
		
		//한달중 마지막날짜 구하기 
		lastDate = adaptee.getActualMaximum(DAY_OF_MONTH);  //adaptee(Calendar)에서 month의 최대값 구하기 => DAY_OF_MONTH에서 최대 숫자 구하기 

		
		//현재 년도와 월 꺼내놓기 
		currentYear = adaptee.get(YEAR); //adaptee(Calendar)에서 year가져오기 
		currentMonth = adaptee.get(MONTH);
		
	
		//클릭시 다음달 전 달, 내년 작년 구하기 
		/* adaptee에서 1을 빼면 전 달 / adapatee(Calendar)에서 2을 더하면 다음달*/
		adaptee.add(MONTH, -1); //전 달, 작년 출력    => month를 -1로 세팅해서 adaptee에 추가 
		beforeYear = adaptee.get(YEAR);
		beforeMonth = adaptee.get(MONTH);
		
		
		adaptee.add(MONTH, 2); //지금 month 상태가 -1로 세팅되어있는 상태이기 때문에 2를 더해야 다음달로 감 
		nextYear = adaptee.get(YEAR);
		nextMonth = adaptee.get(MONTH);

		adaptee.add(MONTH, -1); //달력을 다시 현재 달 상태로 바꿔놓기
		
		
		
		
		
	}

	public Calendar getAdaptee() {
		return adaptee;
	}

	public void setAdaptee(Calendar adaptee) {
		this.adaptee = adaptee;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getDayOfWeekFirst() {
		return dayOfWeekFirst;
	}

	public void setDayOfWeekFirst(int dayOfWeekFirst) {
		this.dayOfWeekFirst = dayOfWeekFirst;
	}

	public int getLastDate() {
		return lastDate;
	}

	public void setLastDate(int lastDate) {
		this.lastDate = lastDate;
	}


	public String[] getWeekDays() {
		return weekDays;
	}

	public void setWeekDays(String[] weekDays) {
		this.weekDays = weekDays;
	}


	public int getBeforeYear() {
		return beforeYear;
	}

	public void setBeforeYear(int beforeYear) {
		this.beforeYear = beforeYear;
	}

	public int getBeforeMonth() {
		return beforeMonth;
	}

	public void setBeforeMonth(int beforeMonth) {
		this.beforeMonth = beforeMonth;
	}

	public int getNextYear() {
		return nextYear;
	}

	public void setNextYear(int nextYear) {
		this.nextYear = nextYear;
	}

	public int getNextMonth() {
		return nextMonth;
	}

	public void setNextMonth(int nextMonth) {
		this.nextMonth = nextMonth;
	}


	public String[] getMonths() {
		return months;
	}

	public void setMonths(String[] months) {
		this.months = months;
	}

	public int getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(int currentYear) {
		this.currentYear = currentYear;
	}

	public int getCurrentMonth() {
		return currentMonth;
	}

	public void setCurrentMonth(int currentMonth) {
		this.currentMonth = currentMonth;
	}
	

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	@Override
	public String toString() {
		//몇년도 몇월달 달력인지  출력하기 
		//return String.format("%1$tY, %1$tB", adaptee);
		return String.format(locale, "%1$tY, %1$tB", adaptee);
		
		//1: 첫번째 argument
		//t: time
		//Y: 네자리 연도
		//B: 월
	}
	
	
	
	
	
	
}
