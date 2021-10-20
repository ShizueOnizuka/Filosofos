public class Filosofo extends Thread
{
   final static int TEMPO_MAXIMO = 10;
   Mesa mesa;
   int filosofo;

   public Filosofo (String nome, Mesa mesadejantar, int fil)
   {
      super(nome);
      mesa = mesadejantar;
      filosofo = fil;
   }

   public void run ()
   {
      int tempo = 0;
      while (true)
      {
         tempo = (int) (Math.random() * TEMPO_MAXIMO);
         pensar(tempo);
         getHashi();
         tempo = (int) (Math.random() * TEMPO_MAXIMO);
         comer(tempo);
         returnHashi();
      }
   }

   public void pensar (int tempo)
   {
      try
      {
         sleep(tempo);
      }
      catch (InterruptedException e)
      {
         System.out.println("O filosofo ficou pensando...");
      }
   }

   public void comer (int tempo)
   {
      try
      {
         sleep(tempo);
      }
      catch (InterruptedException e)
      {
         System.out.println("O filosofo se alimentou");
      }
   }

   public void getHashi()
   {
      mesa.pegarHashi(filosofo);
   }

   public void returnHashi()
   {
      mesa.returningHashi(filosofo);
   }
}