using System;
using System.Collections.Generic;

namespace WebApi.Models
{
    public partial class Cidade
    {
        public Cidade()
        {
            Endereco = new HashSet<Endereco>();
        }

        public int CdCidade { get; set; }
        public int CdEstado { get; set; }
        public string NmCidade { get; set; }
        public DateTime DtCadastro { get; set; }
        public DateTime? DtAtualizacao { get; set; }

        public Estado CdEstadoNavigation { get; set; }
        public ICollection<Endereco> Endereco { get; set; }
    }
}
