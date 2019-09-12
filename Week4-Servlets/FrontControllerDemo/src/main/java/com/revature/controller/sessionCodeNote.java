package com.revature.controller;

public class sessionCodeNote {

	/*ths note are for the authoDemo maven project######################################
	 * front controller will bethe same and same packages and tools
	 * 1) model package
	 * create variables id,username,password,userrole
	 * getter setter... implement serialzable
	 * 2) create 2 html page to be able to login and homepage
	 * login ahs username and pass and button
	 *  3) create new servlet front controller servlet
	 *  4 )cretae request helper
	 *  on (3)do get methode
	 *  get servlet path 
	 *  if path start with static  to send us to thath process
	 *  0n (4)
	 *  create procces get method(http request)
	 *  no we can check if not static page then it going to be request that forwad us to static page or 
	 *  if the path match login go start to login deleget otherwise authenticate them
	 *  create a View DELEGATE CLASS
	 *  HERE return view()
	 *  get the path and do a switch because you can have more
	 *  case (login) forwardto login page by the request dispatcher
	 *  case(Home)  you for home
	 *  
	 *  BACK TO REQUESTHELPER  get instance of view delegeate
	 *  processget ()
	 *  on if (lofin ==path) got  to viewdelaget return view
	 *  else? verify authorization(systout)
	 *  
	 *  we need to create a dao methode to check our database storeded credintial
	 *  8) create DAO PAKHAGE  USERDAO
	 *  so we dont need to get all user because anyone can get acces to them in console
	 *  select usern ans password from table where user=?
	 *  methode public lst <user> getall();
	 *  and rest of dao methodes
	 *  9) userdao implementtion
	 *  we going to mimic the database inside the userdaoImple constructor[public UserDaoImpl]
	 *   get all() list
	 *   getuser by Id () loop thru that loop if you have a large data use "streams"
	 *   getuser by usernameand password()
	 *    the first argument in if statement act as short circuit if its false we dont go to the 2argument
	 * Now we can create  loginservlete  to tie an event to logn bttn    and send an get aythonticatio or unssucful login
	 * create login javascript  to tie event listenin to the button
	 * on javascript the requestbyheader is to read from paramter 
	 * on front controlletr voide DopOST CALL A PROCESSpost
	 *  ON REQUESThLPER CREATE THET METHODE PROCCESpOST
	 *  check if login 
	 *  1) get the path again
	 *  2)switch  if we going to have multiple post\
	 *  case(1) login
	 *  //something to aythenticat so we create a util package inside class authUTIL
	 *  
	 *  IN AUTHutil class()
	 *  public string to return a token (userID AND  password)
	 *   back in request helper in case (LOGIN)
	 *   GET REQUEST =GETPARAMTER(USERNAME AND PASSORD)
	 *   AND PASS THAT TO THE AUTHONTICATE
	 *  CREATE AN INSTANCE OF AUTHuTIL
	 *  TOKEN=..
	 *  
	 *  BACK IN 
	 * ATHUNTICuTIL
	 * CREATE INSTANCE OF USERDAO
	 * ON METHODE AUTHENTICATE
	 * IMPORT USER U=USER DAO GETUSERNAMEAND PASSWORD
	 * IF (U!NULL) SYSTEMOUT USER 
	 * NOW WE NEED TO CONCATNATE USERNAME AND PASSWORD
	 *  ON REQUEST HELPER AS IF TOKEN STATMENT TO CHECK IF NUUL 403 ERROR
	 *  ELSE STATUS 200 AND SET HEADER =TOKEN
	 *  BACK TO JAVASCRIPT ASS LET AUTH=XHR=GETRESPONSE(AUTHORISATON)
	 *  SESSIONsTORAGE .SETANAME (TOEN,AUTH)
	 *  CONSOL LOG IT TO SEE IF YOU GETING IN CLIEN SIDE
	 *  
	 *  NEXT STPE IS MAKE CALL TO HOME PAGE SINCE YOUR AYTHRIZE
	 *   WE CREATE A SPAN IN HOW PAGE TO SAY WELCOM "FLAN"
	 *   ADD <SPAN ID "USER"></SPAN>
	 *   SO WILL RQUIRED TO READ THATH TOKEN{NAME)
	 *  BEFORE IF LOGIN  REQUEST HELPER ASS IF("PATH START WITH API/)
	 *  
	 *   
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
}
