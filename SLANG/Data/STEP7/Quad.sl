FUNCTION NUMERIC Quad( NUMERIC a , NUMERIC b , NUMERIC c )
   NUMERIC n;

   n = b*b - 4.0*a*c;
   IF ( n < 0.0 ) THEN
        return 0.0;
   ELSE 

     IF ( n == 0.0 ) THEN
         return 1.0;
     ELSE
         return 2.0;
     ENDIF
 
   ENDIF 
   return 0.0;


END


FUNCTION BOOLEAN MAIN()
   NUMERIC d;
   d= Quad(1.0,0.0-6.0,9.0);

   IF ( d == 0.0 ) then
         PRINT "No Roots";
   ELSE
       IF ( d  == 1.0 ) then
         PRINT  "Discriminant is zero";
       ELSE
         PRINT  "Two roots are available";
       ENDIF
   ENDIF
END
