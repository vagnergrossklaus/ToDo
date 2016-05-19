package br.eti.vagnergross.todo.api.rest.request;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by vagner on 27/04/16.
 */
@Data
@XmlRootElement
public class TarefaRequest {
    @XmlElement Integer id;
    @XmlElement Integer idUsuario;
    @XmlElement String descricao;
    @XmlElement Integer concluido;
}
