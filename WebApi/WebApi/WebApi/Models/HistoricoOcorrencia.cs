using System;
using System.Collections.Generic;

namespace WebApi.Models
{
    public partial class HistoricoOcorrencia
    {
        public int CdHistoricoocorrencia { get; set; }
        public int CdOcorrencia { get; set; }
        public string DsHistoricoocorrencia { get; set; }
        public DateTime DtCadastro { get; set; }

        public Ocorrencia CdOcorrenciaNavigation { get; set; }
    }
}
