package com.uds.desafioapi;

import java.math.BigDecimal;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.uds.desafio.api.model.ItemPedido;
import com.uds.desafio.api.model.Produto;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {Produto.class, ItemPedido.class})
public class DesafioApiApplicationTests {
	
	@Test
	public void deve_retornar_fibonacci_posicao_2() {
		Produto produto = new Produto();
		produto.setNome("Produto Teste");
		produto.setPreco(BigDecimal.valueOf(100));
		
		ItemPedido item = new ItemPedido();
		item.setPercentualDesconto(BigDecimal.TEN);
		item.setQuantidade(BigDecimal.TEN);
		item.setProduto(produto);
		item.atualizarValores();
		assertEquals(BigDecimal.valueOf(900.0), item.getTotal());
	}
}
