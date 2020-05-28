package br.com.weslleyesanto.youtubeclone.model;

/*
* Escapsular dados de retorno da API, por isso usamos public para o atributos
*
* */

import java.util.List;

public class Resultado {

    public String regionCode;
    public PageInfo pageInfo;
    public List<Item> items;
}
