����   4 Z  com/revature/fourpillars/Apple  com/revature/fourpillars/Food bumps Ljava/lang/Integer; <init> (I)V Code
    
     getNumOfFoodItems ()I
    java/lang/Integer   valueOf (I)Ljava/lang/Integer;	     LineNumberTable LocalVariableTable this  Lcom/revature/fourpillars/Apple; num I hashCode
     
    prime result StackMapTable equals (Ljava/lang/Object;)Z
  ( % &
 * , + java/lang/Object - . getClass ()Ljava/lang/Class;
  ( obj Ljava/lang/Object; other fallDown ()V	 6 8 7 java/lang/System 9 : out Ljava/io/PrintStream; < java/lang/StringBuilder
  > ? @ toString ()Ljava/lang/String;
 B D C java/lang/String  E &(Ljava/lang/Object;)Ljava/lang/String;
 ; G  H (Ljava/lang/String;)V J  apples fall off the tree.
 ; L M N append -(Ljava/lang/String;)Ljava/lang/StringBuilder;
 ; >
 Q S R java/io/PrintStream T H println rotSomewhere W The apple(s) rot on the ground. 
SourceFile 
Apple.java !                 	   M     *� 
**� � � �           
                           	   �     "<*� =h*� � � 
*� � !`=�                           "       "     #   $    �    �      % &  	   �     E*+� �*+� '� �*� )+� )� �+� M*� � ,� � �*� ,� � /� ��       6                 #  *  1   3 ! A " C #         E       E 0 1  # " 2   $    	�    3 4  	   N      � 5� ;Y*� � =� A� FI� K� O� P�       
    *  +               U 4  	   7     	� 5V� P�       
    /  1        	      X    Y