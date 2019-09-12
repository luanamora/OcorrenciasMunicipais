using System;
using System.Collections.Generic;

namespace WebApi.Models
{
    public partial class HistoricoSenha
    {
        public int CdHistoricosenha { get; set; }
        public int CdUsuario { get; set; }
        public string DsSenha { get; set; }
        public DateTime DtCadastro { get; set; }

        public Usuario CdUsuarioNavigation { get; set; }
    }
}
