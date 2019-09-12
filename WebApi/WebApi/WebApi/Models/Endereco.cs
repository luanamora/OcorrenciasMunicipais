using System;
using System.Collections.Generic;

namespace WebApi.Models
{
    public partial class Endereco
    {
        public Endereco()
        {
            Ocorrencia = new HashSet<Ocorrencia>();
        }

        public int CdEndereco { get; set; }
        public string NrCep { get; set; }
        public string DsBairro { get; set; }
        public string DsLogradouro { get; set; }
        public string DsNumero { get; set; }
        public string DsComplemento { get; set; }
        public int CdCidade { get; set; }
        public DateTime DtCadastro { get; set; }
        public DateTime? DtAtualizacao { get; set; }

        public Cidade CdCidadeNavigation { get; set; }
        public ICollection<Ocorrencia> Ocorrencia { get; set; }
    }
}
