package produto;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Classe que representa um repositório de produtos usando ArrayList como
 * estrutura sobrejacente. Alguns métodos (atualizar, remover e procurar) ou
 * executam com sucesso ou retornam um erro. Para o caso desde exercício, o erro
 * será representado por uma RuntimeException que não precisa ser declarada na
 * clausula "throws" do mos metodos.
 *
 * @author Adalberto
 */
public class RepositorioProdutoArrayList {

	/**
	 * A estrutura onde os produtos sao mantidos. Voce nao precisa se preocupar
	 * por enquanto com o uso de generics em ArrayList.
	 */
	private ArrayList produtos;

	public RepositorioProdutoArrayList(int size) {
		super();
		this.produtos = new ArrayList();
	}

	/**
	 * Recebe o codigo do produto e devolve o indice desse produto no array ou
	 * -1 caso ele nao se encontre no array. Esse método é util apenas na
	 * implementacao com arrays por questoes de localizacao. Outras classes que
	 * utilizam outras estruturas internas podem nao precisar desse método.
	 * 
	 * @param codigo
	 * @return
	 */
	private int procurarIndice(int codigo) {
		Produto produto = new Produto(codigo, null, 0, null);
		return this.produtos.indexOf(produto);
	}

	/**
	 * Recebe o codigo e diz se tem produto com esse codigo armazenado
	 * 
	 * @param codigo
	 * @return
	 */
	public boolean existe(int codigo) {
		Produto produto = new Produto(codigo, null, 0, null);
		return this.produtos.contains(produto);
	}

	/**
	 * Insere um novo produto (sem se preocupar com duplicatas)
	 */
	public void inserir(Produto produto) {
		if (!this.produtos.contains(produto)) {
			this.produtos.add(produto);
		}
	}

	/**
	 * Atualiza um produto armazenado ou retorna um erro caso o produto nao
	 * esteja no array. Note que, para localizacao, o código do produto será
	 * utilizado.
	 */
	public void atualizar(Produto produto) {
		int indiceProduto = procurarIndice(produto.getCodigo());
		if (indiceProduto == -1) {
			throw new NoSuchElementException("O elemento não está no Array!");
		}
		this.produtos.add(indiceProduto, produto);
	}

	/**
	 * Remove produto com determinado codigo, se existir, ou entao retorna um
	 * erro, caso contrário. Note que a remoção NÃO pode deixar "buracos" no
	 * array.
	 * 
	 * @param codigo
	 */
	public void remover(int codigo) {
		int indiceProduto = this.produtos.indexOf(new Produto(codigo, null, 0, null));
		this.produtos.remove(indiceProduto);
	}

	/**
	 * Retorna um produto com determinado codigo ou entao um erro, caso o
	 * produto nao esteja armazenado
	 * 
	 * @param codigo
	 * @return retornao produto caso esteja na lista ou null caso contrário. 
	 */
	public Produto procurar(int codigo) {
		int indiceProduto = produtos.indexOf(new Produto(codigo, null, 0 , null));
		if (indiceProduto == -1) {
			return null;
		}
		return (Produto) this.produtos.get(indiceProduto);
	}
}
