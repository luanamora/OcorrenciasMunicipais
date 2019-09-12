using System;
using System.Collections.Generic;

namespace WebApi.Models
{
    public partial class ImagemUsuario
    {
        public int CdImagem { get; set; }
        public int CdUsuario { get; set; }
        public string NmImagem { get; set; }
        public string DsCaminho { get; set; }
        public string DsTipo { get; set; }
        public DateTime DtCadastro { get; set; }
        public DateTime? DtAtualizacao { get; set; }

        public Usuario CdUsuarioNavigation { get; set; }
    }
}
