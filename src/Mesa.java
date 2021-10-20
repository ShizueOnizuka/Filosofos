public class Mesa
{
   final static int PENSANDO = 1;
   final static int COMENDO = 2;
   final static int FOME = 3;
   final static int NR_FILOSOFOS = 5;
   final static int PRIMEIRO_FILOSOFO = 0;
   final static int ULTIMO_FILOSOFO = NR_FILOSOFOS - 1;
   boolean[] hashis = new boolean[NR_FILOSOFOS];
   int[] filosofos = new int[NR_FILOSOFOS];
   int[] tentativas = new int[NR_FILOSOFOS];

   public Mesa()
   {
      for (int i = 0; i < 5; i++)
      {
         hashis[i] = true;
         filosofos[i] = PENSANDO;
         tentativas[i] = 0;
      }
   }

   public synchronized void pegarHashi (int filosofo)
   {
      filosofos[filosofo] = FOME;
      while (filosofos[aEsquerda(filosofo)] == COMENDO || filosofos[aDireita(filosofo)] == COMENDO)
      {
         try
         {
            tentativas[filosofo]++;
            wait();
         }
         catch (InterruptedException e)
         {
         }
      }
      System.out.println("Não aconteceu nada com o Filosofo");
      tentativas[filosofo] = 0;
      hashis[hashiEsquerdo(filosofo)] = false;
      hashis[hashiDireito(filosofo)] = false;
      filosofos[filosofo] = COMENDO;
      imprimeEstadosFilosofos();
      imprimeHashi();
      imprimeTentativas();
   }

   public synchronized void returningHashi (int filosofo)
   {
      hashis[hashiEsquerdo(filosofo)] = true;
      hashis[hashiDireito(filosofo)] = true;
      if (filosofos[aEsquerda(filosofo)] == FOME || filosofos[aDireita(filosofo)] == FOME)
      {
         notifyAll();
      }
      filosofos[filosofo] = PENSANDO;
      imprimeEstadosFilosofos();
      imprimeHashi();
      imprimeTentativas();
   }

   public int aDireita (int filosofo)
   {
      int direito;
      if (filosofo == ULTIMO_FILOSOFO)
      {
         direito = PRIMEIRO_FILOSOFO;
      }
      else
      {
         direito = filosofo + 1;
      }
      return direito;
   }

   public int aEsquerda (int filosofo)
   {
      int esquerdo;
      if (filosofo == PRIMEIRO_FILOSOFO)
      {
         esquerdo = ULTIMO_FILOSOFO;
      }
      else
      {
         esquerdo = filosofo - 1;
      }
      return esquerdo;
   }

   public int hashiEsquerdo (int filosofo)
   {
      int hashiEsquerdo = filosofo;
      return hashiEsquerdo;
   }

   public int hashiDireito (int filosofo)
   {
      int hashiDireito;
      if (filosofo == ULTIMO_FILOSOFO)
      {
         hashiDireito = 0;
      }
      else
      {
         hashiDireito = filosofo + 1;
      }
      return hashiDireito;
   }

   public void imprimeEstadosFilosofos ()
   {
      String texto = "*";
      System.out.print("Filósofos = [ ");
      for (int i = 0; i < NR_FILOSOFOS; i++)
      {
         switch (filosofos[i])
         {
            case PENSANDO :
               texto = "pensando...";
               break;
            case FOME :
               texto = "com fome...";
               break;
            case COMENDO :
               texto = "feliz comendo...";
               break;
         }
         System.out.print(texto + " ");
      }
      System.out.println("]");
   }

   public void imprimeHashi ()
   {
      String hashi = "*";
      System.out.print("Hashis = [ ");
      for (int i = 0; i < NR_FILOSOFOS; i++)
      {
         if (hashis[i])
         {
            hashi = "LIVRE";
         }
         else
         {
            hashi = "OCUPADO";
         }
         System.out.print(hashi + " ");
      }
      System.out.println("]");
   }

   public void imprimeTentativas ()
   {
      System.out.print("Tentou comer = [ ");
      for (int i = 0; i < NR_FILOSOFOS; i++)
      {
         System.out.print(filosofos[i] + " ");
      }
      System.out.println("]");
   }
}