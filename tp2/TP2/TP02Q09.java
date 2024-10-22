import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;

class Pokemon {
    int id;
    String name;
    int generation;
    String description;
    ArrayList<String> types;
    ArrayList<String> abilities;
    double weight;
    double height;
    int captureRate;
    boolean isLegendary;
    String captureDate;

    // Construtor da classe Pokemon
    Pokemon(String[] atributosPre, String[] atributosPos, String abilitiesLine) throws Exception {
        this.id = Integer.parseInt(atributosPre[0]);
        this.generation = Integer.parseInt(atributosPre[1]);
        this.name = atributosPre[2];
        this.description = atributosPre[3];

        // Convertendo strings separadas por vírgulas para ArrayList
        if (atributosPre[5].equals("")) {
            this.types = new ArrayList<>(Arrays.asList(("'" + atributosPre[4] + "'" + "," + atributosPre[5]).split(",")));
        } else {
            this.types = new ArrayList<>(Arrays.asList(("'" + atributosPre[4] + "'" + "," + "'" + atributosPre[5] + "'").split(",")));
        }
        this.abilities = new ArrayList<>(Arrays.asList(abilitiesLine.split(",")));

        this.weight = atributosPos[1].equals("") ? 0.0 : Double.parseDouble(atributosPos[1]);
        this.height = atributosPos[2].equals("") ? 0.0 : Double.parseDouble(atributosPos[2]);
        this.captureRate = Integer.parseInt(atributosPos[3]);
        this.isLegendary = atributosPos[4].equals("1");
        this.captureDate = atributosPos[5];
    }

    // Método para buscar um Pokémon pelo ID
    public static Pokemon searchById(int id, Pokemon[] pokemon) {
        for (Pokemon p : pokemon) {
            if (p.id == id) {
                return p;
            }
        }
        return null;
    }
}

class TP02Q09 {
    // Função para verificar se a entrada é "FIM"
    public static boolean isFim(String id) {
        return id.equals("FIM");
    }

    public static void heapify(Pokemon[] pokemons, int tam, int indiceRaiz) {
        int menor = indiceRaiz; // Use menor em vez de maior para crescente
        int filhoEsquerda = 2 * indiceRaiz + 1;
        int filhoDireita = 2 * indiceRaiz + 2;
    
        // Se o filho esquerdo é menor que a raiz ou igual em altura mas o nome é menor
        if ((filhoEsquerda < tam && (pokemons[filhoEsquerda].height < pokemons[menor].height) ||
            (pokemons[filhoEsquerda].height == pokemons[menor].height && 
            pokemons[filhoEsquerda].name.compareTo(pokemons[menor].name) < 0))) {
            menor = filhoEsquerda;
        }
    
        // Se o filho direito é menor que a raiz ou igual em altura mas o nome é menor
        if ((filhoDireita < tam && (pokemons[filhoDireita].height < pokemons[menor].height) ||
            (pokemons[filhoDireita].height == pokemons[menor].height && 
            pokemons[filhoDireita].name.compareTo(pokemons[menor].name) < 0))) {
            menor = filhoDireita;
        }
    
        // Se a raiz não é o menor, trocar com o filho menor
        if (menor != indiceRaiz) {
            Pokemon temp = pokemons[indiceRaiz];
            pokemons[indiceRaiz] = pokemons[menor];
            pokemons[menor] = temp;
    
            // Recursivamente heapify a subárvore afetada
            heapify(pokemons, tam, menor);
        }
    }
    
    public static Pokemon[] heapSort(int tam, Pokemon[] pokemons) {
        // Construir o heap inicial
        for (int i = tam / 2 - 1; i >= 0; i--) {
            heapify(pokemons, tam, i);
        }
    
        // Extrair elementos um por um do heap
        for (int i = tam - 1; i > 0; i--) {
            // Mover a raiz atual para o final do array
            Pokemon temp = pokemons[0];
            pokemons[0] = pokemons[i];
            pokemons[i] = temp;
    
            // Chamar heapify no heap reduzido
            heapify(pokemons, i, 0);
        }
    
        return pokemons;
    }
    
    public static void main(String[] args) throws Exception {
        RandomAccessFile file = new RandomAccessFile("/tmp/pokemon.csv", "r"); // Para mandar no verde é "/tmp/pokemon.csv"
        file.readLine(); // Pular a primeira linha que não é útil
        Pokemon[] pokemon = new Pokemon[801]; // Criando um array de Pokémon para guardar todos

        // Lendo os dados do arquivo e criando os objetos de Pokemon
        for (int i = 0; i < 801; i++) {
            String line = file.readLine();
            String[] atributosTmp = line.split("\\[|\\]"); // Separando as linhas pelo colchete
            String abilitiesLine = atributosTmp[1];
            abilitiesLine = abilitiesLine.replaceAll(", ", ","); // Para não ficar com espaço duplo na hora de criar a lista com abilities

            String[] atributosPre = atributosTmp[0].split(","); // Atributos de Pokémon
            String[] atributosPos = atributosTmp[2].split(","); // Atributos de Pokémon

            pokemon[i] = new Pokemon(atributosPre, atributosPos, abilitiesLine);
        }
        file.close();

        String id = MyIO.readLine();
        Pokemon[] pokemon1 = new Pokemon[51];
        int pos = 0;

        while (!isFim(id)) {
            pokemon1[pos++] = Pokemon.searchById(Integer.parseInt(id), pokemon);
            id = MyIO.readLine();
        }

        // Ordenação usando HeapSort
        pokemon1 = Arrays.copyOf(pokemon1, pos); // Reduzir o tamanho do array
        pokemon1 = heapSort(pokemon1.length, pokemon1);

        // Exibir a saída
        for (Pokemon poke : pokemon1) {
            if (poke != null) {
                MyIO.println("[#" + poke.id + " -> " + poke.name + ": " + poke.description +
                    " - " + poke.types + " - " + poke.abilities + " - " + poke.weight + "kg - " +
                    poke.height + "m - " + poke.captureRate + "% - " + poke.isLegendary +
                    " - " + poke.generation + " gen] - " + poke.captureDate);
            }
        }
    }
}
