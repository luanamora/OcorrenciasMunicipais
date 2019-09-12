using System;
using System.Collections.Generic;

namespace WebApi.Models
{
    public partial class UsuarioAreaatendimento
    {
        public int CdUsuarioatendimento { get; set; }
        public int CdAreaatendimento { get; set; }
        public int CdUsuario { get; set; }
        public DateTime DtCadastro { get; set; }
        public string DtAtualizacao { get; set; }

        public AreaAtendimento CdAreaatendimentoNavigation { get; set; }
        public Usuario CdUsuarioNavigation { get; set; }
    }
}
