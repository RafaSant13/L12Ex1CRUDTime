package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Jogador;
import model.Time;

public class JogadorDao implements ICrud<Jogador>{
	
	private GenericDao gDao;
	
	public JogadorDao (GenericDao gDao) {
		this.gDao = gDao;
	}

	@Override
	public void inserir(Jogador j) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "INSERT INTO jogador VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, j.getId());
		ps.setString(2, j.getNome());
		ps.setString(3, j.getDataNasc().toString());
		ps.setFloat(4, j.getAltura());
		ps.setFloat(5, j.getPeso());
		ps.setInt(6, j.getTime().getCodigo());
		ps.execute();
		ps.close();
		c.close();
	}

	@Override
	public void atualizar(Jogador j) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "UPDATE jogador SET nome =?, data_nasc=?, altura=?, peso=?, TimeCodigo=? WHERE id =?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, j.getNome());
		ps.setString(2, j.getDataNasc().toString());
		ps.setFloat(3, j.getAltura());
		ps.setFloat(4, j.getPeso());
		ps.setInt(5, j.getTime().getCodigo());
		ps.setInt(6, j.getId());
		ps.execute();
		ps.close();
		c.close();
		
	}

	@Override
	public void excluir(Jogador j) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String SQL = "DELETE FROM jogador WHERE id=?";
		PreparedStatement ps = c.prepareStatement(SQL);
		ps.setInt(1, j.getId());
		ps.execute();
		ps.close();
		c.close();
		
	}

	@Override
	public Jogador consultar(Jogador j) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "SELECT j.id AS id, j.nome AS nome, j.data_nasc AS data_nasc, "
				+ "j.altura AS altura, j.peso AS peso, t.codigo AS codTime,"
				+ "t.nome AS nomeTime, t.cidade AS cidadeTime FROM jogador j, time t"
				+ " WHERE j.TimeCodigo = t.codigo AND j.id =?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, j.getId());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			Time t = new Time();
			
			t.setCodigo(rs.getInt("codTime"));
			t.setNome(rs.getString("nomeTime"));
			t.setCidade(rs.getString("cidadeTime"));
			
			j.setId(rs.getInt("id"));
			j.setNome(rs.getString("nome"));
			j.setDataNasc(LocalDate.parse(rs.getString("data_nasc")));
			j.setAltura(rs.getFloat("altura"));
			j.setPeso(rs.getFloat("peso"));
			j.setTime(t);
		}
		rs.close();
		ps.close();
		c.close();
		return j;
	}

	@Override
	public List<Jogador> listar() throws SQLException, ClassNotFoundException {
		List<Jogador> jogadores = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "SELECT j.id AS id, j.nome AS nome, j.data_nasc AS data_nasc, "
				+ "j.altura AS altura, j.peso AS peso, t.codigo AS codTime,"
				+ "t.nome AS nomeTime, t.cidade AS cidadeTime FROM jogador j, time t "
				+ "WHERE j.TimeCodigo = t.codigo";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Jogador j = new Jogador();
			Time t = new Time();
			
			t.setCodigo(rs.getInt("codTime"));
			t.setNome(rs.getString("nomeTime"));
			t.setCidade(rs.getString("cidadeTime"));
			
			j.setId(rs.getInt("id"));
			j.setNome(rs.getString("nome"));
			j.setDataNasc(LocalDate.parse(rs.getString("data_nasc")));
			j.setAltura(rs.getFloat("altura"));
			j.setPeso(rs.getFloat("peso"));
			j.setTime(t);
		}
		rs.close();
		ps.close();
		c.close();
		return jogadores;
	}
}
