using System;
using System.Collections.Generic;

namespace WebApi.Models
{
    public partial class AreaAtendimento
    {
        public AreaAtendimento()
        {
            Ocorrencia = new HashSet<Ocorrencia>();
            TelefoneAreaatendimento = new HashSet<TelefoneAreaatendimento>();
            UsuarioAreaatendimento = new HashSet<UsuarioAreaatendimento>();
        }

        public int CdAreaatendimento { get; set; }
        public int CdAreaatuacao { get; set; }
        public string DsAreaatendimento { get; set; }
        public string DsEmail { get; set; }
        public DateTime DtCadastro { get; set; }
        public DateTime? DtAtualizacao { get; set; }

        public AreaAtuacao CdAreaatuacaoNavigation { get; set; }
        public ICollection<Ocorrencia> Ocorrencia { get; set; }
        public ICollection<TelefoneAreaatendimento> TelefoneAreaatendimento { get; set; }
        public ICollection<UsuarioAreaatendimento> UsuarioAreaatendimento { get; set; }
    }
}
