package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Jogador;
import model.Time;
import persistance.GenericDao;
import persistance.JogadorDao;
import persistance.TimeDao;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/jogador")
public class JogadorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JogadorServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String erro = "";
		List<Time> times = new ArrayList<>();

		GenericDao gDao = new GenericDao();
		TimeDao tDao = new TimeDao(gDao);

		try {
			times = tDao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			erro = e.getMessage();
		} finally {

			request.setAttribute("erro", erro);
			request.setAttribute("times", times);
		}
		RequestDispatcher rd = request.getRequestDispatcher("jogador.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Entrada
		String cmd = request.getParameter("botao");
		String id = request.getParameter("codigo");
		String nome = request.getParameter("nome");
		String dataNasc = request.getParameter("dataNasc");
		String altura = request.getParameter("altura");
		String peso = request.getParameter("peso");
		String time = request.getParameter("time");
				
		//Saída
		String saida = "";
		String erro = "";
		Jogador j = new Jogador();
		List<Jogador> jogadores = new ArrayList<>();
		List<Time> times = new ArrayList<>();
		
		GenericDao gDao = new GenericDao();
		TimeDao tDao = new TimeDao(gDao);

		try {
			times = tDao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			erro = e.getMessage();
		} finally {

			request.setAttribute("erro", erro);
			request.setAttribute("times", times);
		}
		
		if (!cmd.contains("Listar")) {
			j.setId(Integer.parseInt(id));
		}
		if (cmd.contains("Cadastrar") || cmd.contains("Alterar")) {
			j.setNome(nome);
			j.setDataNasc(LocalDate.parse(dataNasc));
			j.setAltura(Float.parseFloat(altura));
			j.setPeso(Float.parseFloat(peso));
			Time t = new Time();
			t.setCodigo(Integer.parseInt(time));
			try {
				t = tDao.consultar(t);
			} catch (SQLException | ClassNotFoundException e) {
				erro = e.getMessage();
			}
			j.setTime(t);
			
		}
		try {
			if (cmd.contains("Cadastrar")) {
				cadastrarJogador(j);
				saida = "Jogador cadastrado com sucesso!";
				j = null;
			}
			if (cmd.contains("Alterar")) {
				alterarJogador(j);
				saida = "Jogador alterado com sucesso!";
				j = null;
			}
			if (cmd.contains("Excluir")) {
				excluirJogador(j);
				saida = "Jogador excluído com sucesso!";
				j = null;
			}
			if (cmd.contains("Buscar")) {
				j = buscarJogador(j);
			}
			if (cmd.contains("Listar")) {
				jogadores = listarJogadores();
			}
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			request.setAttribute("saida", saida);
			request.setAttribute("erro", erro);
			request.setAttribute("jogador", j);
			request.setAttribute("jogadores", jogadores);
			
			RequestDispatcher rd = request.getRequestDispatcher("jogador.jsp");
			rd.forward(request, response);
		}
	}

	private void cadastrarJogador(Jogador j) throws SQLException, ClassNotFoundException {
		GenericDao gDao = new GenericDao();
		JogadorDao jDao = new JogadorDao(gDao);
		jDao.inserir(j);
	}

	private void alterarJogador(Jogador j) throws SQLException, ClassNotFoundException {
		GenericDao gDao = new GenericDao();
		JogadorDao jDao = new JogadorDao(gDao);
		jDao.atualizar(j);
	}

	private void excluirJogador(Jogador j) throws SQLException, ClassNotFoundException {
		GenericDao gDao = new GenericDao();
		JogadorDao jDao = new JogadorDao(gDao);
		jDao.excluir(j);
	}

	private Jogador buscarJogador(Jogador j) throws SQLException, ClassNotFoundException {
		GenericDao gDao = new GenericDao();
		JogadorDao jDao = new JogadorDao(gDao);
		j = jDao.consultar(j);
		return j;
	}

	private List<Jogador> listarJogadores() throws SQLException, ClassNotFoundException {
		GenericDao gDao = new GenericDao();
		JogadorDao jDao = new JogadorDao(gDao);
		List<Jogador> jogadores = jDao.listar();
		return jogadores;
	}

}
