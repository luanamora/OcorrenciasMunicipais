using System;
using System.Collections.Generic;

namespace WebApi.Models
{
    public partial class TelefoneAreaatendimento
    {
        public int CdTelefoneareaatendimento { get; set; }
        public int CdAreaatendimento { get; set; }
        public string NrTelefone { get; set; }
        public string NrDdd { get; set; }
        public string DsTelefone { get; set; }
        public DateTime DtCadastro { get; set; }
        public DateTime? DtAtualizacao { get; set; }

        public AreaAtendimento CdAreaatendimentoNavigation { get; set; }
    }
}
