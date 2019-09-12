using System;
using System.Collections.Generic;

namespace WebApi.Models
{
    public partial class ImagemOcorrencia
    {
        public int CdImagem { get; set; }
        public int CdOcorrencia { get; set; }
        public string NmImagem { get; set; }
        public string DsCaminho { get; set; }
        public string DsTipo { get; set; }
        public DateTime DtCadastro { get; set; }
        public DateTime? DtAtualizacao { get; set; }

        public Ocorrencia CdOcorrenciaNavigation { get; set; }
    }
}
